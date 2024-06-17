package vn.com.lol.nautilus.commons.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;
import vn.com.lol.common.dto.request.QueryParam;
import vn.com.lol.common.dto.request.URLComponent;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebclientUtil {

    private final WebClient client;

    public <R> CompletableFuture<R> getRequest(URLComponent urlComponent, ParameterizedTypeReference<R> responseType) {
        return client.get().uri(uriBuilder -> {
                    UriBuilder builder = uriBuilder
                            .scheme(urlComponent.getScheme())
                            .host(urlComponent.getHost())
                            .path(urlComponent.getPath());

                    for (QueryParam queryParam : urlComponent.getQueryParams()) {
                        builder = builder.queryParam(queryParam.getKey(), queryParam.getValue());
                    }
                    return builder.build();
                }).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchangeToMono(response -> {
                    if (response.statusCode().is2xxSuccessful()) {
                        return response.bodyToMono(responseType);
                    } else {
                        return response.bodyToMono(String.class).flatMap(error -> {
                            log.error("Get request ERROR {}", error);
                            return Mono.error(new RuntimeException(error));
                        });
                    }
                })
                .toFuture();

    }

    public <T, R> CompletableFuture<R> postRequest(String url, T input, ParameterizedTypeReference<R> responseType) {
        return client.post()
                .uri(URI.create(url))
                .body(BodyInserters.fromPublisher(Mono.just(input), new ParameterizedTypeReference<>() {
                }))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + null)
                .exchangeToMono(response -> {
                    if (response.statusCode().is2xxSuccessful()) {
                        return response.bodyToMono(responseType);
                    } else {
                        return response.bodyToMono(String.class).flatMap(error -> {
                            log.error("Post request ERROR {}", error);
                            return Mono.error(new RuntimeException(error));
                        });
                    }
                })
                .toFuture();
    }

    public <R> CompletableFuture<R> postRequestFormData(String url, MultiValueMap<String, ?> multiValueMap, ParameterizedTypeReference<R> responseType) {

        return client.post()
                .uri(URI.create(url))
                .body(BodyInserters.fromMultipartData(multiValueMap))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.MULTIPART_FORM_DATA_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + null)
                .exchangeToMono(response -> {
                    if (response.statusCode().is2xxSuccessful()) {
                        return response.bodyToMono(responseType);
                    } else {
                        return response.bodyToMono(String.class).flatMap(error -> {
                            log.error("Post request form data ERROR {}", error);
                            return Mono.error(new RuntimeException(error));
                        });
                    }
                })
                .toFuture();
    }
}
