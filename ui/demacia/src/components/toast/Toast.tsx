import React, { useEffect, useState } from "react";
import Snackbar, { SnackbarOrigin } from "@mui/material/Snackbar";
import { Slide, SlideProps } from "@mui/material";
import { createRoot } from "react-dom/client";

export interface IToastProps {
  errorCode: string | undefined;
  errorMessage: string | undefined;
  toastId?: string;
  onClose?: (toastId: string) => void;
}
interface State extends SnackbarOrigin {
  open: boolean;
}

const Toast: React.FC<IToastProps> = ({
  errorCode = "",
  errorMessage = "",
  onClose = () => {},
  toastId = "",
}) => {
  const [state, setState] = useState<State>({
    open: true,
    horizontal: "right",
    vertical: "top",
  });

  const SlideTransition = (props: SlideProps) => {
    return <Slide {...props} direction="right" />;
  };

  useEffect(() => {
    const timeOut = setTimeout(() => {
      setState((prevState) => {
        return {
          ...prevState,
          open: false,
        };
      });
    }, 1500);
    return () => clearTimeout(timeOut);
  }, []);

  return (
    <Snackbar
      anchorOrigin={{ vertical: state.vertical, horizontal: state.horizontal }}
      open={state.open}
      message={`${errorCode} - ${errorMessage}`}
      key={state.vertical + state.horizontal}
      TransitionComponent={SlideTransition}
      onClose={() => onClose(toastId)}
    />
  );
};

const toast = (props: IToastProps) => {
  const containerDomNode = document.createElement("div");
  const currentNodeID = `toast-${Date.now()}`;
  containerDomNode.id = currentNodeID;
  document.body.appendChild(containerDomNode);
  const root = createRoot(containerDomNode);

  const removeElementById = (id: string) => {
    const element = document.getElementById(id);
    if (element) {
      element.parentNode?.removeChild(element);
    }
  };
  const onCloseToast = (toastId: string) => {
    removeElementById(toastId);
  };

  root.render(<Toast {...props} toastId={currentNodeID} onClose={onCloseToast} />);
};

export default toast;
