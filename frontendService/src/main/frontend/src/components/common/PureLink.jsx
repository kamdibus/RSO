import React from 'react';
import { Link } from 'react-router-dom';

export function PureLink(props) {
  return (
    <Link style={{color: 'inherit', textDecoration: 'inherit'}} {...props} />
  )
}