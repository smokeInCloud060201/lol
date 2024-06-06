import React, {useState} from "react";

const App = () => {

    const [userName, setUserName] = useState();
    const [password, setPassword] = useState();

    return (
        <div>
            <form action={"/perform_login"}>
                <label>User name </label>
                <input type={"text"} value={userName} onChange={(e) => setUserName(e?.target?.value)}/>

                <label>Password</label>
                <input type={"password"} value={password} onChange={(e) => setPassword(e?.target?.value)}/>
                <button type={"submit"} onClick={() => {console.log("Handle submit", userName, password)}}>Submit</button>
            </form>
        </div>
    )
}

export default App;