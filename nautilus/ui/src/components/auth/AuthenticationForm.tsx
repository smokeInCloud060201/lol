import React, { useEffect, useMemo, useRef, useState } from 'react';
import {
    Button,
    FormControl,
    Box,
    FormLabel,
    Input,
    InputLabel,
    Typography,
    TextField
} from '@mui/material';
import SignInFormImage from 'src/access/images/SignInForm.jpg';
import { useSpring } from '@react-spring/web';
import { animated } from 'react-spring';
import { getElementInfo } from '../../utils';
import { Formik, Form } from 'formik';
import * as Yup from 'yup';
import { PASSWORD_REGEX } from '../../constant';

type TForm = 'SignUp' | 'SignIn';

interface ISignUp {
    name: string;
    email: string;
    password: string;
    confirmPassword: string;
}

const AuthenticationForm: React.FC = () => {
    const [formType, setFormType] = useState<TForm>('SignIn');
    const [componentRendered, setComponentRendered] = useState(false);
    const ref = useRef<HTMLDivElement>(null);

    const refInfo = useMemo(() => {
        return getElementInfo(ref);
    }, [ref, componentRendered]);

    const styles = useSpring({
        x: formType === 'SignIn' ? 0 : refInfo?.width + 8,
        y: formType === 'SignIn' ? 0 : -refInfo?.height / 2
    });

    useEffect(() => {
        setComponentRendered(true);
    }, []);

    const handleSubmit = (values: ISignUp) => {
        console.log('values', values);
    };

    const signUpSchema = Yup.object().shape({
        name: Yup.string().required('Name is required'),
        email: Yup.string().email('Invalid email').required('Email is required'),
        password: Yup.string()
            .required('Password is required')
            .matches(
                PASSWORD_REGEX,
                'Password should be at least 1 Uppercase letter, 1 Lowercase letter, 1 number, 1 Special letter, size from 8 - 32 letters'
            ),
        confirmPassword: Yup.string()
            .oneOf([Yup.ref('password')], 'Passwords must match')
            .required('Confirm password is required')
    });

    const signInSchema = Yup.object().shape({
        email: Yup.string().email('Invalid email').required('Email is required'),
        password: Yup.string().required('Password is required')
    });

    return (
        <div className={'auth-form'}>
            <Box
                style={{
                    width: '100%',
                    display: 'flex',
                    justifyItems: 'center',
                    alignItems: 'center',
                    flexDirection: 'row',
                    gap: '8px',
                    position: 'relative'
                }}>
                <Box style={{ width: '50%' }}>
                    <Typography
                        marginY={2}
                        variant="h5"
                        component="h1"
                        align="center"
                        style={{ fontWeight: 'bold' }}>
                        Join LoL World Today
                    </Typography>
                    <Formik
                        initialValues={{
                            name: '',
                            email: '',
                            password: '',
                            confirmPassword: ''
                        }}
                        onSubmit={handleSubmit}
                        validationSchema={signUpSchema}>
                        {(props) => {
                            console.log(props.errors);
                            return (
                                <Form>
                                    <Box mb={2}>
                                        <TextField
                                            name={'name'}
                                            variant="outlined"
                                            fullWidth
                                            value={props.values.name}
                                            onChange={props.handleChange}
                                            required
                                        />
                                    </Box>
                                    <Box mb={2}>
                                        <TextField
                                            name={'email'}
                                            variant="outlined"
                                            fullWidth
                                            value={props.values.email}
                                            onChange={props.handleChange}
                                            required
                                            type="email"
                                        />
                                    </Box>
                                    <Box mb={2}>
                                        <TextField
                                            name={'password'}
                                            variant="outlined"
                                            fullWidth
                                            value={props.values.password}
                                            onChange={props.handleChange}
                                            required
                                            type="password"
                                        />
                                    </Box>
                                    <Box mb={2}>
                                        <TextField
                                            name={'confirmPassword'}
                                            variant="outlined"
                                            fullWidth
                                            value={props.values.confirmPassword}
                                            onChange={props.handleChange}
                                            required
                                            type="password"
                                        />
                                    </Box>
                                    <Box mt={3}>
                                        <Button
                                            variant="contained"
                                            color="primary"
                                            type="submit"
                                            fullWidth>
                                            Sign Up
                                        </Button>
                                    </Box>
                                </Form>
                            );
                        }}
                    </Formik>
                </Box>
                <animated.div style={{ width: '50%', position: 'absolute', ...styles }}>
                    <img style={{ width: '100%', aspectRatio: '8/9' }} src={SignInFormImage} />
                </animated.div>
                <Box style={{ width: '50%' }} ref={ref}>
                    <FormLabel style={{ color: 'white' }}>Sign In</FormLabel>
                    <FormControl className={'form'}>
                        <InputLabel htmlFor={'username'} required={true}>
                            User name
                        </InputLabel>
                        <Input id={'username'} />

                        <Button name={'Login'} style={{ width: '100%' }}>
                            Submit
                        </Button>

                        <Button
                            onClick={() =>
                                setFormType((pre) => {
                                    if (pre === 'SignIn') {
                                        return 'SignUp';
                                    }
                                    return 'SignIn';
                                })
                            }>
                            Change form type
                        </Button>
                    </FormControl>
                </Box>
            </Box>
        </div>
    );
};

export default AuthenticationForm;
