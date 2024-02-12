import React, {useEffect, useState} from 'react';
import {Navigate} from 'react-router-dom';
import app_logo from './app_logo.png';
import './LogoPage.css'

/*
    redirectLogin: hold current state value
    setRedirectLogin: function use to update state
*/
const LogoPage = () => {
    const [redirectLogin, setRedirectLogin] = useState(false);

    //  Use timer to show logo for 2s before redirect user to login page
    //  Change setRedirection to true after 2s
    useEffect(() => {
        const timer = setTimeout(() => {
            setRedirectLogin(true);
        }, 2000);

        return () => {
            clearTimeout(timer);
        };
    }, []);

    // Redirect the user to the login page after 2s
    return (
        <div className='logo-page'>
            <div className='welcome-message'>
                <h1>Welcome To</h1>
                <img src={app_logo} alt='app_logo' className='app_logo' style={{ width: '90%'}}/>
            </div>
            {/* If redirectLogin is true, we will direct user to the login page using react Navigate*/}
            {redirectLogin && <Navigate to = '/login' />}
        </div>
    );
};

export default LogoPage;