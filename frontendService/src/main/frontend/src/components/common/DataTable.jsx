import MUITable from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import MUIPaper from '@material-ui/core/Paper';
import React from 'react';
import styled from 'styled-components';
import { withRouter } from 'react-router';

export function DataTable({ columns, rows }) {
  return (
    <Paper>
      <Table>
        <TableHead>
          <TableRow>
            {columns.map(column => <TableCell key={column.name}>{column.label}</TableCell>)}
          </TableRow>
        </TableHead>
        <TableBody>
          {rows.map((row, rowIndex) => <Row key={rowIndex} data={row.data} href={row.href} columns={columns} />)}
        </TableBody>
      </Table>
    </Paper>
  );
}

const Row = withRouter(
  ({ data, columns, href, history }) => {
    return (
      <TableRow onClick={() => href && history.push(href)} hover={true}>
        {columns.map(column => <TableCell key={column.name}>{data[column.name] || ''}</TableCell>)}
      </TableRow>
    )
  }
)

const Table = styled(MUITable)`
  min-width: 700px;
`
const Paper = styled(MUIPaper)`
  width: 100%;
  margin-top: 3px;
  overflow-x: 'auto';
  margin-left: auto;
  margin-right: auto;
`
