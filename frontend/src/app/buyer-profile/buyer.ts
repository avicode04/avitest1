export interface Buyer{
    buyerEmail:String;
    buyerName:String;
    buyerImage:String;
    buyerGender:String;
    buyerDob:Date;
    buyerPhone:Number;
    buyerHomeAddress:String;
    buyerOfficeAddress:String;
    incartProductsList: IncartProductsList[];
    orderedProductsList: OrderedProductsList[];
    returnedProductsList: ReturnedProductsList[];
}

export interface IncartProductsList {
    productId: string;
    productPrice: number;
    productQuantity: number;
  }
  export interface OrderedProductsList{
    productId: string;
    productPrice: number;
    productQuantity: number;
  }
  export interface ReturnedProductsList{
    productId: string;
    productPrice: number;
    productQuantity: number;
    orderId:String;
    customerId:String;
    sellerId:String;
  }