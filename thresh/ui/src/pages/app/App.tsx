import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { selectToken, setToken } from '../../store/slices/auth.slice';
import { useLoginMutation } from '../../services/auth.service';
import { ILoginRequest, ILoginResponse } from '../../interface/IAuth';
import { IBaseResponse } from '../../interface/IBase';
import { FetchBaseQueryError } from '@reduxjs/toolkit/query';

const App: React.FC = () => {
    const dispatch = useDispatch();
    const token = useSelector(selectToken);

    const [formData, setFormData] = useState<ILoginRequest>();
    const [login] = useLoginMutation();

    const handleLogin = () => {
        login({ email: formData?.email ?? '', password: formData?.password ?? '' })
            .then((res: { data: IBaseResponse<ILoginResponse> }) => {
                console.log('res', res);
                return res.data.data;
            })
            .then((res: ILoginResponse) => console.log(res))
            .catch((err: FetchBaseQueryError) => console.log(err));
    };

    useEffect(() => {
        console.log('form data', formData);
    }, [formData]);

    return (
        <div>
            <h1>Token is : {token}</h1>
            <button
                type={'button'}
                onClick={() => {
                    dispatch(setToken('123'));
                }}>
                Click me to change token
            </button>

            <form>
                <div className={'border-2'}>
                    <label htmlFor="username">Username</label>
                    <input
                        type="text"
                        id="username"
                        name="username"
                        onChange={(e) =>
                            setFormData((pre: any) => {
                                return { ...pre, email: e.target.value };
                            })
                        }
                    />
                </div>
                <div className={'border-2'}>
                    <label htmlFor="password">Password</label>
                    <input
                        type="password"
                        id="password"
                        name="password"
                        onChange={(e) =>
                            setFormData((pre: any) => {
                                return { ...pre, password: e.target.value };
                            })
                        }
                    />
                </div>

                <button type={'button'} onClick={() => handleLogin()}>
                    Submit
                </button>
            </form>
        </div>
    );
};

export default App;
