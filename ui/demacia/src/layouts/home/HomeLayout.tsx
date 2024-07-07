import React, { ReactNode } from "react";
import { Box, Container, Drawer } from "@mui/material";

export interface IBaseLayout {
  children: ReactNode;
}

const HomeLayout: React.FC<IBaseLayout> = ({ children }: any) => {
  const drawerWith = 240;

  return (
    <Container maxWidth={false} style={{ padding: 4, minHeight: "100vh" }} sx={{ display: "flex" }}>
      <Drawer
        variant={"permanent"}
        open={true}
        sx={{
          display: { xs: "block", sm: "block" },
          "& .MuiDrawer-paper": { boxSizing: "border-box", width: drawerWith },
        }}
      ></Drawer>

      <Container maxWidth={false} style={{ paddingTop: 16, height: "100%" }}>
        <Box>{children}</Box>
      </Container>
    </Container>
  );
};

export default HomeLayout;
