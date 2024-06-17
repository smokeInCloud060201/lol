import React from "react";
import {useDispatch, useSelector} from "react-redux";
import {selectToken, setToken} from "../../store/slices/auth.slice";



const App: React.FC = () => {
    const dispatch = useDispatch();
    const token = useSelector(selectToken);



    return (
        <div>
            <h1>Token is : {token}</h1>
            <button onClick={() => {dispatch(setToken('123'))}}>Click me to change token</button>
        </div>
    );

}

export default App;