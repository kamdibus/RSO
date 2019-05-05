import MUITable from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import MUIPaper from '@material-ui/core/Paper';
import React from 'react';
import styled, { css } from 'styled-components';

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
          {rows.map((row, rowIndex) => (
            <TableRow key={row.key != null ? row.key : rowIndex}>
              {columns.map(column => <TableCell key={column.name}>{row[column.name] || ''}</TableCell>)}
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </Paper>
  );
}

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
