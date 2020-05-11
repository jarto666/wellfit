import React from 'react';
import { useAuth0 } from 'utils/auth/auth0';

import fat from './fat.jpg';

import styles from './index.module.css';

const Profile = () => {
  const { loading, user } = useAuth0();

  if (loading || !user) {
    return <div>Loading...</div>;
  }

  return (
    <div className={styles.container}>
      <div className={styles.info}>
        <img src={user.picture} alt="Profile" />

        <h2>{user.name}</h2>
        <p>{user.email}</p>
        <code>{JSON.stringify(user, null, 2)}</code>
      </div>

      <div className={styles.body}>
        <img src={fat} alt="Жиробас" className={styles.image} />
      </div>
    </div>
  );
};

export default Profile;
