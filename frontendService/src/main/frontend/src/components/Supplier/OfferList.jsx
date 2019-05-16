import { DataRow } from "../Layout/DataRow";
import { LoadingOverlay } from "../common/Loading/Overlay";
import { DataTable } from "../common/DataTable";
import React from 'react';
import { SupplierService } from "../../backend/supplier/service/supplier";

export class OfferList extends React.Component {
  state = {
    offers: [],
    loading: true
  }
  componentDidMount() {
    SupplierService
      .getOffers()
      .then(offers => this.setState({ offers, loading: false }))
  }
  render() {
    const { offers, loading } = this.state
    const rows = offers
      .map(data => ({ data, href: `/supplier/offers/${data.id}` }))
    return (
      <DataRow>
        {loading && <LoadingOverlay />}
        <DataTable
          columns={[
            { name: 'supplier', label: "Supplier" },
            { name: 'ratio', label: "Ratio" },
            { name: 'expirationDate', label: "Expiration date" },
          ]}
          rows={rows}
        />
      </DataRow>
    )
  }
}
