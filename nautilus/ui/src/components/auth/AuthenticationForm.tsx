import React from 'react';
import { Button, FormControl, Input, InputLabel } from '@mui/material';

const AuthenticationForm: React.FC = () => {
    return (
        <div className={'auth'}>
            <FormControl className={'form'}>
                <InputLabel htmlFor={'username'} required={true}>
                    User name
                </InputLabel>
                <Input id={'username'} />

                <Button name={'Login'} style={{ width: '100%' }}>
                    Submit
                </Button>
            </FormControl>
        </div>
    );
};

export default AuthenticationForm;
