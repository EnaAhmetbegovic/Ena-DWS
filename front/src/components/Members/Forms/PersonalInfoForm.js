import React from 'react';
import { Form, Select, Input } from 'semantic-ui-react';

export default class PersonalInfoForm extends React.PureComponent {
  render() {
    const {
      jmbg,
      firstName,
      lastName,
      gender,
      handleInputChange,

      jmbgErr,
      firstNameErr,
      lastNameErr,
      genderErr
    } = this.props;

    return (
      <Form noValidate>
        <Form.Field
          required
          control={Input}
          label="JMBG"
          placeholder="JMBG"
          value={jmbg}
          name="jmbg"
          onChange={handleInputChange}
          error={jmbgErr}
        />
        <Form.Field
          required
          control={Input}
          label="Ime"
          placeholder="Ime"
          value={firstName}
          name="firstName"
          onChange={handleInputChange}
          error={firstNameErr}
        />
        <Form.Field
          required
          control={Input}
          label="Prezime"
          placeholder="Prezime"
          value={lastName}
          name="lastName"
          onChange={handleInputChange}
          error={lastNameErr}
        />
        <Form.Field
          required
          control={Select}
          clearable
          label="Spol"
          options={this.props.genders}
          placeholder="Spol"
          onChange={this.props.handleInputChange}
          name="gender"
          value={gender}
          error={genderErr}
        />
      </Form>
    );
  }
}
