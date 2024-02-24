import React, {useEffect} from 'react';
import {Navigate} from "react-router-dom";

// This will be used to prevent users from routing to the homepage without authentication
function ProtectedRoute ({children}) {
    // This will ensure that the session will be cleared whenever user clicks on the back button (moving away from homepage)
    useEffect(() => {
        // Clear session when component unmounts
        return () => {
            sessionStorage.removeItem('user');
        };
    }, []);
    const userData = JSON.parse(sessionStorage.getItem("user"));
    if (!userData) {
        return <div>Please <a href={"/login"}> log in </a> to access this page</div>;
    }
    return children;
}

export default ProtectedRoute;