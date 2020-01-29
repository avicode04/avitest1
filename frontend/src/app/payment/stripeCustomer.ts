 export class StripeCustomer {
    id: string;
    object: string;
    address: any;
    balance: number;
    created: number;
    currency: string;
    default_source: any;
    delinquent: boolean;
    description: string;
    discount: any;
    email: any;
    invoice_prefix: string;
    invoice_settings: Invoicesettings;
    livemode: boolean;
    metadata: Metadata;
    name: any;
    phone: any;
    preferred_locales: any[];
    shipping: any;
    sources: Sources;
    subscriptions: Sources;
    tax_exempt: string;
    tax_ids: Sources;
    tax_info: any;
    tax_info_verification: any;
  }
  
 export interface Sources {
    object: string;
    data: any[];
    has_more: boolean;
    url: string;
  }
  
 export interface Metadata {
  }
  
 export interface Invoicesettings {
    custom_fields: any;
    default_payment_method: any;
    footer: any;
  }