import Authentication from '../pages/auth/Authentication';
import React from 'react';

interface IRoute {
    path: string;
    page: React.FC;
}

export const routes: IRoute[] = [{ path: '/auth', page: Authentication }];
