import React, { useState } from 'react';
import LinearProgress from '@material-ui/core/LinearProgress';

export default function withLoader(WrappedComponent, message) {
    return ((props) => {
        const [isLoading, setIsLoading] = useState(true);
        console.log("Loading state: ", isLoading);
        return (
            <React.Fragment>
                {isLoading && <LinearProgress />}
                <WrappedComponent {...props} setIsLoading={setIsLoading} />
            </React.Fragment>
        );
    });
}