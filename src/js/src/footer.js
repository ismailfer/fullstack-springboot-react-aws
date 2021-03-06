import { Avatar, Button } from 'antd';
import React from 'react';
import Container from './container';

import './footer.css';

const footer = (props) =>
(
    <div className='footer'>
        <Container>
            {props.numberOfStudents !== undefined ? 
                <Avatar 
                    style={{backgroundColor: '#f56a00', marginRight: '5px'}} 
                    size='large'>{props.numberOfStudents}</Avatar> : null
            }
            <Button type='primary' onClick={() => props.handleAddStudentClickEvent()}
                >Add new student +</Button>
        </Container>
    </div>
);

export default footer;