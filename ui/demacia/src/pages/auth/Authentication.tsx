import React from "react";
import { Container } from "@mui/material";
import { AuthenticationForm } from "../../components/auth";

const Authentication: React.FC = () => {
  return (
    <Container
      maxWidth={false}
      className={"screen auth"}
      style={{
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
        height: "100vh",
      }}
    >
      <AuthenticationForm />
    </Container>
  );
};

export default Authentication;
