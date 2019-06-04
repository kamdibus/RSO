import { DataRow } from "../Layout/DataRow";
import { LoadingOverlay } from "../common/Loading/Overlay";
import { DataTable } from "../common/DataTable";
import React from 'react';
import { SupplierService } from "../../backend/supplier/service/supplier";

class Offers extends React.Component {
  state = {
    offers: [],
    loading: true
  }
  componentDidMount() {
    this.props
      .getData()
      .then(offers => this.setState({ offers, loading: false }))
  }
  render() {
    const { offers, loading } = this.state
    const { buildHref } = this.props
    const rows = offers
      .map(data => ({
        data,
        href: buildHref && buildHref(data.id)
      }))
    return (
      <DataRow>
        {loading && <LoadingOverlay />}
        <DataTable
          columns={[
            { name: 'id', label: "Id" },
            { name: 'discount', label: "Discount" },
            { name: 'status', label: "Status" },
            { name: 'creationDate', label: "Creation date" },
            { name: 'expirationDate', label: "Expiration date" },
            { name: 'invoiceId', label: "Invoice id" },
            { name: 'supplierId', label: "Supplier id" },
            { name: 'consumerId', label: "Consumer id" },
          ]}
          rows={rows}
        />
      </DataRow>
    )
  }
}

export function OfferList(props) {
  const getData = () => SupplierService.getOffers()
  return <Offers getData={getData} {...props} />
}

export function OfferHistory(props) {
  const getData = () => SupplierService.getOffersHistory()
  return <Offers getData={getData} {...props} />
}