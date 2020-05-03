import React from 'react';

import styles from './index.module.css';

const AuthForm = () => (
  <div className={styles.wrapper}>
    <form className={styles.form}>
      <label>
        <input className={styles.input} type="text" placeholder="login" />
      </label>

      <label>
        <input className={styles.input} type="text" placeholder="password" />
      </label>
    </form>
  </div>
);

export default AuthForm;
