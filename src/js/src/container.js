import React from 'react';

// Container will add space around the table

const Container = (props) => (
    <div style={{width: '1200px', margin: '0 auto', textAlign: 'center'}}>
        {props.children}
    </div>
);

export default Container;