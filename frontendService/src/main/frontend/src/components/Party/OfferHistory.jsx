import { DataRow } from "../Layout/DataRow";
import { LoadingOverlay } from "../common/Loading/Overlay";
import { DataTable } from "../common/DataTable";
import React from 'react';
import { SupplierService } from "../../backend/supplier/service/supplier";

export class OfferHistory extends React.Component {
  state = {
    offers: [],
    loading: true
  }
  componentDidMount() {
    SupplierService
      .getOffersHistory()
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
            { name: 'priceGross', label: "Price gross" },
            { name: 'expirationDate', label: "Expiration date" },
            { name: 'status', label: "Status" },
          ]}
          rows={rows}
        />
      </DataRow>
    )
  }
}
