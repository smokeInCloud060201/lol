import React, { useEffect } from 'react';
import { Route, Routes, useNavigate } from 'react-router-dom';
import Authentication from '../pages/auth/Authentication';
import { useSelector } from 'react-redux';
import { selectToken } from '../store/slices/auth.slice';

const AppRouter: React.FC = () => {
    const isLogin = !!useSelector(selectToken);
    const navigate = useNavigate();

    useEffect(() => {
        if (!isLogin) {
            navigate('/auth');
        }
    }, [isLogin]);

    return (
        <Routes>
            <Route path="/auth" element={<Authentication />} errorElement={<Authentication />} />
        </Routes>
    );
};

export default AppRouter;
