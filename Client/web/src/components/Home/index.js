import React from 'react';
import useAuth0 from 'auth/useAuth';

const Home = () => {
  const { login, isAuthenticated } = useAuth0();
  return (
    <div className="container">
      {isAuthenticated && <h4>You are logged in!</h4>}
      {!isAuthenticated && (
        <h4>
          You are not logged in! Please
          <button type="button" style={{ cursor: 'pointer' }} onClick={login}>
            Log In
          </button>
          to continue.
        </h4>
      )}
    </div>
  );
};

export default Home;
