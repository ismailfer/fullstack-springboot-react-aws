import React from 'react';

import { Formik } from 'formik';

import { Input, Button, Tag } from 'antd';

import { addNewStudent } from '../client';


const inputBottomMargin = {marginBottom: '10px'}

const tagStyle = {backgroundColor: '#f50', color: 'white', ...inputBottomMargin}

// https://formik.org/docs/overview

const AddStudentForm = (props) =>
(

    <Formik
    initialValues={{ firstName: '', lastName: '', email: '', gender: '' }}
    validate={values => {

        const errors = {};

        if (!values.firstName) 
        {
            errors.firstName = 'First name required!';
        } 

        if (!values.lastName) 
        {
            errors.lastName = 'Last name required!';
        } 

        if (!values.email) 
        {
            errors.email = 'Email required';
        } 
        else if (!/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i.test(values.email)) 
        {
            errors.email = 'Invalid email address';
        }

        if (!values.gender) 
        {
            errors.gender = 'Gender required!';
        } 
        else if (!['MALE', 'male', 'FEMALE', 'female'].includes(values.gender)) 
        {
            errors.gender = 'Gender must be (MALE, male, FEMALE, female)';
        }                

        return errors;

    }}

    onSubmit={(student, { setSubmitting }) => 
        {
        addNewStudent(student).then(() => {
            //alert(JSON.stringify(student));
            props.onSuccess();
            setSubmitting(false);
            })

        }
    }
    
    // end of Formik tag
    >

    {({
        values,
        errors,
        touched,
        handleChange,
        handleBlur,
        handleSubmit,
        isSubmitting,
        submitForm,
        isValid
        /* and other goodies */

    }) => (

        <form onSubmit={handleSubmit}>

        <Input
            name="firstName"
            onChange={handleChange}
            onBlur={handleBlur}                    
            value={values.firstName}
            placeholder='First name. E.g John'
            style={inputBottomMargin}
        />
        {errors.firstName && touched.firstName && <Tag style={tagStyle}>{errors.firstName}</Tag>}

        <Input
            name="lastName"
            onChange={handleChange}
            onBlur={handleBlur}                    
            value={values.lastName}
            placeholder='Last name. E.g Smith'
            style={inputBottomMargin}
        />
        {errors.lastName && touched.lastName && <Tag style={tagStyle}>{errors.lastName}</Tag>}

        <Input
            name="email"
            onChange={handleChange}
            onBlur={handleBlur}                    
            value={values.email}
            placeholder='Email. E.g example@gmail.com'
            style={inputBottomMargin}
        />
        {errors.email && touched.email && <Tag style={tagStyle}>{errors.email}</Tag>}

        <Input
            name="gender"
            onChange={handleChange}
            onBlur={handleBlur}                    
            value={values.gender}
            placeholder='Gender. E.g Male or Female'
            style={inputBottomMargin}
        />
        {errors.gender && touched.gender && <Tag style={tagStyle}>{errors.gender}</Tag>}


        <Button type="submit" 
            onClick={() => submitForm() } 
            disabled={isSubmitting | (touched && !isValid)}
            >
            Submit
        </Button>

        </form>

    )}

    </Formik>

);


export default AddStudentForm;