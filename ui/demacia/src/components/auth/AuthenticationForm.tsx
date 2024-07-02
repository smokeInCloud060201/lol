import React, { useRef, useState } from "react";
import { Button, Box, Typography, TextField } from "@mui/material";
import { useSpring, animated } from "@react-spring/web";
import { PASSWORD_REGEX } from "../../constant";
import { Formik, Form, FormikProps } from "formik";
import * as Yup from "yup";
import { useLoginMutation } from "../../services/auth.service";

type TForm = "SignUp" | "SignIn";

interface ISignUp {
  name: string;
  email: string;
  password: string;
  confirmPassword: string;
}

interface ISignIn {
  email: string;
  password: string;
}

const AuthenticationForm: React.FC = () => {
  const [formType, setFormType] = useState<TForm>("SignIn");
  const ref = useRef<HTMLDivElement>();

  const [login] = useLoginMutation();

  const boxSignInAnimatedStyles: any = useSpring({
    from: {
      right: formType === "SignIn" ? "0" : "200px",
    },
    to: async (next) => {
      await next({
        right: formType === "SignIn" ? "200px" : "0",
      });
    },
  });

  const boxSignUpAnimatedStyles: any = useSpring({
    from: {
      right: formType === "SignIn" ? "0" : "200px",
    },
    to: async (next) => {
      await next({
        right: formType === "SignIn" ? "200px" : "0",
      });
    },
  });

  const handleSubmit = (values: ISignUp) => {
    console.log("values", values);
  };

  const handleLogin = (values: ISignIn) => {
    login(values)
      .unwrap()
      .then((res) => {
        console.log(res);
      })
      .catch((err) => console.log(err));
  };

  const signUpSchema = Yup.object().shape({
    name: Yup.string().required("Name is required"),
    email: Yup.string().email("Invalid email").required("Email is required"),
    password: Yup.string()
      .required("Password is required")
      .matches(
        PASSWORD_REGEX,
        "Password should be at least 1 Uppercase letter, 1 Lowercase letter, 1 number, 1 Special letter, size from 8 - 32 letters",
      ),
    confirmPassword: Yup.string()
      .oneOf([Yup.ref("password")], "Passwords must match")
      .required("Confirm password is required"),
  });

  const signInSchema = Yup.object().shape({
    email: Yup.string().email("Invalid email").required("Email is required"),
    password: Yup.string().required("Password is required"),
  });

  return (
    <div className={"auth-form"}>
      <Box
        ref={ref}
        style={{
          width: "100%",
          display: "flex",
          justifyItems: "center",
          alignItems: "center",
          flexDirection: "row",
          gap: "16px",
          position: "relative",
          overflow: "hidden",
        }}
      >
        <animated.div style={{ ...boxSignInAnimatedStyles, flex: 1 }}>
          <Box>
            <Typography
              marginY={2}
              variant="h5"
              component="h1"
              align="center"
              style={{ fontWeight: "bold" }}
            >
              Sign In
            </Typography>
            <Formik
              initialValues={{
                email: "",
                password: "",
              }}
              onSubmit={handleLogin}
              validationSchema={signInSchema}
            >
              {(props: FormikProps<ISignIn>) => (
                <Form>
                  <Box mb={2}>
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
                  <Box mb={2}>
                    <TextField
                      name={"password"}
                      variant="outlined"
                      fullWidth
                      value={props.values.password}
                      onChange={props.handleChange}
                      required
                      type="password"
                    />
                  </Box>
                  <Box mt={3}>
                    <Button variant="contained" color="primary" type="submit" fullWidth>
                      Sign In
                    </Button>
                  </Box>
                  <Box mt={3}>
                    <Button
                      onClick={() => setFormType("SignUp")}
                      variant="outlined"
                      color="primary"
                      fullWidth
                    >
                      Switch to Sign Up
                    </Button>
                  </Box>
                </Form>
              )}
            </Formik>
          </Box>
        </animated.div>

        <animated.div style={{ ...boxSignUpAnimatedStyles, flex: 1 }}>
          <Box>
            <Typography
              marginY={2}
              variant="h5"
              component="h1"
              align="center"
              style={{ fontWeight: "bold" }}
            >
              Sign Up
            </Typography>
            <Formik
              initialValues={{
                name: "",
                email: "",
                password: "",
                confirmPassword: "",
              }}
              onSubmit={handleSubmit}
              validationSchema={signUpSchema}
            >
              {(props: FormikProps<ISignUp>) => (
                <Form>
                  <Box mb={2}>
                    <TextField
                      name={"name"}
                      variant="outlined"
                      fullWidth
                      value={props.values.name}
                      onChange={props.handleChange}
                      required
                    />
                  </Box>
                  <Box mb={2}>
                    <TextField
                      name={"email"}
                      variant="outlined"
                      fullWidth
                      value={props.values.email}
                      onChange={props.handleChange}
                      required
                      type="email"
                    />
                  </Box>
                  <Box mb={2}>
                    <TextField
                      name={"password"}
                      variant="outlined"
                      fullWidth
                      value={props.values.password}
                      onChange={props.handleChange}
                      required
                      type="password"
                    />
                  </Box>
                  <Box mb={2}>
                    <TextField
                      name={"confirmPassword"}
                      variant="outlined"
                      fullWidth
                      value={props.values.confirmPassword}
                      onChange={props?.handleChange}
                      required
                      type="password"
                    />
                  </Box>
                  <Box mt={3}>
                    <Button variant="contained" color="primary" type="submit" fullWidth>
                      Sign Up
                    </Button>
                  </Box>
                  <Box mt={3}>
                    <Button
                      onClick={() => setFormType("SignIn")}
                      variant="outlined"
                      color="primary"
                      fullWidth
                    >
                      Switch to Sign In
                    </Button>
                  </Box>
                </Form>
              )}
            </Formik>
          </Box>
        </animated.div>
      </Box>
    </div>
  );
};

export default AuthenticationForm;
