export const getElementInfo = (ref: React.RefObject<any>) => {
    if (!ref) {
        return null;
    }
    return {
        width: ref?.current?.clientWidth,
        height: ref?.current?.clientHeight,
        left: ref?.current?.clientLeft,
        top: ref?.current?.clientTop,
        oLeft: ref?.current?.offsetLeft,
        oTop: ref?.current?.offsetTop,
        oWidth: ref?.current?.offsetWidth,
        oHeight: ref?.current?.offsetHeight
    };
};
