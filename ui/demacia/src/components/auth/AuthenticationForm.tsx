import React from "react";
import { Button, Box, TextField, Container } from "@mui/material";
import { Formik, Form, FormikProps, FormikHelpers } from "formik";
import * as Yup from "yup";
import { useLoginMutation } from "../../services/auth.service";
import SignInFormImage from "../../access/images/SignInForm.jpg";
import { useDispatch } from "react-redux";
import { setToken } from "../../store/slices/auth.slice";
import { useNavigate } from "react-router-dom";

interface ISignIn {
  email: string;
  password: string;
}

const initialValues = {
  email: "",
  password: "",
};

const AuthenticationForm: React.FC = () => {
  const [login] = useLoginMutation();
  const dispatch = useDispatch();
  const navigator = useNavigate();
  const handleLogin = (values: ISignIn, action: FormikHelpers<ISignIn>) => {
    login(values)
      .unwrap()
      .then((res) => {
        const token = res?.data?.accessToken;
        dispatch(setToken(token));
        action.resetForm();
        navigator("/dashboard");
      })
      .catch((err) => console.log(err));
  };

  const signInSchema = Yup.object().shape({
    email: Yup.string().email("Invalid email").required("Email is required"),
    password: Yup.string().required("Password is required"),
  });

  return (
    <Container
      style={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        padding: "40px 20px",
        boxShadow: "0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19)",
      }}
    >
      <Box
        style={{
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          flexDirection: "row-reverse",
          gap: 16,
          position: "relative",
          overflow: "hidden",
          flexWrap: "wrap",
          flex: "1 1",
        }}
      >
        <Box
          style={{
            flex: 1,
          }}
        >
          <Formik
            initialValues={initialValues}
            onSubmit={handleLogin}
            onReset={() => initialValues}
            validationSchema={signInSchema}
          >
            {(props: FormikProps<ISignIn>) => (
              <Form>
                <Box mb={4}>
                  <TextField
                    label={"email"}
                    name={"email"}
                    variant="outlined"
                    fullWidth
                    value={props.values.email}
                    onChange={props.handleChange}
                    required
                    type="email"
                  />
                </Box>
                <Box mb={4}>
                  <TextField
                    label={"password"}
                    name={"password"}
                    variant="outlined"
                    fullWidth
                    value={props.values.password}
                    onChange={props.handleChange}
                    required
                    type="password"
                  />
                </Box>
                <Box mt={4}>
                  <Button
                    variant="contained"
                    color="primary"
                    type="submit"
                    fullWidth
                    // onClick={() => handleLogin(props.values)}
                  >
                    Sign In
                  </Button>
                </Box>
                <Box mt={4}>
                  <Button
                    disableRipple
                    variant="text"
                    color="primary"
                    style={{ textDecoration: "underline", backgroundColor: "transparent" }}
                    fullWidth
                  >
                    Forgot password ?
                  </Button>
                </Box>
              </Form>
            )}
          </Formik>
        </Box>
        <Box style={{ flex: 1 }}>
          <img src={SignInFormImage} alt="Sign In Form" style={{ width: "100%" }} />
        </Box>
      </Box>
    </Container>
  );
};

export default AuthenticationForm;
