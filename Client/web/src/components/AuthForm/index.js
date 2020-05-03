import React, { useState } from 'react';

import styles from './index.module.css';

const AuthForm = () => {
  const [isRegistration, setIsRegistration] = useState(false);

  return (
    <div className={styles.wrapper}>
      <form className={styles.form}>
        <label>
          <input
            className={styles.input}
            type="text"
            placeholder="Login"
          />
        </label>

        <label>
          <input
            className={styles.input}
            type="password"
            placeholder="Password"
          />
        </label>

        <button
          className={styles.button}
          type="submit"
          onClick={(e) => { e.preventDefault(); }}
        >
          Log in
        </button>

        <span className={styles.formLabel}>
          {isRegistration ? 'Have an account?' : 'Don`t have an account?'}
          <button
            className={styles.modeButton}
            type="button"
            onClick={() => { setIsRegistration((prev) => !prev); }}
          >
            {isRegistration ? 'log in' : 'sign up'}
          </button>
        </span>
      </form>
    </div>
  );
};

export default AuthForm;
