//import logo from './logo.svg';
import './App.css';
import { Component } from 'react';

import Container from './container';

import Footer from './footer';

import AddStudentForm from './forms/AddStudentForm';
 
import { errorNotification } from './notfication';

import { getAllStudents } from './client';

import {
  Table,
  Avatar,
  Spin,
  Icon,
  Modal,
  Empty
} from 'antd';

/*
function App() 
{
return <h1>Hello World Spring Boot & React</h1>
}
*/

class App extends Component
{
  state = {
    students: [],
    isFetching: false,
    isAddStudentModalVisible: false
  }
 
  componentDidMount ()
  {
    this.fetchStudents();
  }

  openAddStudentModal = () => this.setState({isAddStudentModalVisible: true})

  closeAddStudentModal = () => this.setState({isAddStudentModalVisible: false})

  fetchStudents = () =>
  {
    this.setState({
      isFetching: true
    });

    // get all students and print them to the browser console
    getAllStudents()
      .then(res => res.json()
      .then(students => {
        
        //console.log(students);

        this.setState({
          students,
          isFetching: false
        });

      }))
      .catch(error =>
        {
          const message = error.message;
          const description = error.error.message;
          
          console.log(description);

          this.setState({isFetching: false});

          errorNotification(message, description);

        });

  }

  render()
  {
    //const antIcon = <Icon type="loading" style={{fontSize: 24}} />

    const { students, isFetching, isAddStudentModalVisible } = this.state;

    const commonElements = () => (
      <div>
        <Modal
          title='Add new student'
          visible={isAddStudentModalVisible}
          onOk={this.closeAddStudentModal}
          onCancel={this.closeAddStudentModal}
          width={1000}
          >

            <AddStudentForm 
               onSuccess={() => {
                 this.closeAddStudentModal();
                 this.fetchStudents();
                }}
                onFailure={(err) => {
                    console.log(JSON.stringify(err));

                    const errMsg = err.message;
                    const errDesc = err.httpStatus;

                    errorNotification(errMsg, errDesc)
                }} 
               />

        </Modal>
        
        <Footer 
          numberOfStudents={students.length}
          handleAddStudentClickEvent={this.openAddStudentModal}
          />          
      </div>
    )

    if (isFetching)
    {
      return (
        <Container>
          <Spin />
        </Container>
      );
    }

    if (students && students.length > 0)
    {
      /*
      return students.map((student, index) =>
      {
        return (
          <div id={index}>
            <h2>{student.studentId}</h2>
            <p>{student.firstName}</p>
            <p>{student.lastName}</p>
            <p>{student.gender}</p>
            <p>{student.email}</p>
          </div>
        )
      })
      */



      const columns = [
        {
          title: '',
          key: 'avatar',
          render: (text, student) =>
          (
            <Avatar size='large'>
              {student.firstName.charAt(0).toUpperCase()}{student.lastName.charAt(0).toUpperCase()}
            </Avatar>
          )
        },
        {
          title: 'StudentId',
          dataIndex: "studentId",
          key: 'studentId'
        },
        {
          title: 'First Name',
          dataIndex: "firstName",
          key: 'firstName' 
        },
        {
          title: 'Last Name',
          dataIndex: "lastName",
          key: 'lastName'
        },
        {
          title: 'Email',
          dataIndex: "email",
          key: 'email'
        },
        {
          title: 'Gender',
          dataIndex: "gender",
          key: 'gender'
        }                        
      ];

      return (

        <Container>
          <Table 
            dataSource={students} 
            columns={columns} 
            pagination={false}
            rowKey='stude{}ntId' 
            style={{marginBottom: '100px'}}
            />
          
          {commonElements()}

        </Container>
      );

    }
 
    return (
      <Container>
        <Empty description={<h1>No students found</h1>} />
        {commonElements()}        
      </Container>
    )     

  } 
}

export default App;
  