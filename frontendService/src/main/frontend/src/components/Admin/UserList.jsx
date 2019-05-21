import { DataRow } from "../Layout/DataRow";
import { LoadingOverlay } from "../common/Loading/Overlay";
import { DataTable } from "../common/DataTable";
import React from 'react';
import { UserService } from "../../backend/user/service/user";

export class UserList extends React.Component {
  state = {
    users: [],
    loading: true
  }
  componentDidMount() {
    UserService
      .getUsers()
      .then(users => this.setState({ users, loading: false }))
  }
  render() {
    const { users, loading } = this.state
    const rows = users
      .map(data => ({ data, href: `/admin/users/${data.id}` }))
    return (
      <DataRow>
        {loading && <LoadingOverlay />}
        <DataTable
          columns={[
            { name: 'name', label: "Name" },
            { name: 'nip', label: "Nip" },
          ]}
          rows={rows}
        />
      </DataRow>
    )
  }
}
