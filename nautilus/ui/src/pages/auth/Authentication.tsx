import React from 'react';
import { Box, Container } from '@mui/material';
import { AuthenticationForm } from '../../components/auth';

const Authentication: React.FC = () => {
    return (
        <Container maxWidth={false} className={'screen auth'}>
            <div className={'wrapper'}>
                <Box>
                    <AuthenticationForm />
                </Box>
            </div>
        </Container>
    );
};

export default Authentication;
