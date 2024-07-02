package vn.com.lol.lulu;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class BlobController {

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World!";
    }
}
