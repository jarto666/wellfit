import React, { useEffect } from 'react';
import PropTypes from 'prop-types';

import useAuth0 from 'auth/useAuth';

const Callback = ({ location }) => {
  const { handleAuthentication } = useAuth0();

  useEffect(() => {
    if (/access_token|id_token|error/.test(location.hash)) {
      handleAuthentication();
    }
  }, [handleAuthentication, location]);

  return (
    <div>loading</div>
  );
};

Callback.propTypes = {
  location: PropTypes.shape({
    hash: PropTypes.string,
  }),
};

Callback.defaultProps = {
  location: {},
};

export default Callback;
