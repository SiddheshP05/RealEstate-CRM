export type Lead={id:number;customerName:string;mobile:string;email?:string;city?:string;budget?:number;configuration?:string;leadSource?:string;salesExecutive?:string;status:string;enquiryDate:string};
export type Unit={id:number;unitNumber:string;projectName:string;wing:string;floor:number;configuration:string;carpetArea:number;builtUpArea:number;price:number;parking:boolean;amenities:string;status:string};
export type Dashboard={totalLeads:number;newLeads:number;qualifiedLeads:number;availableUnits:number;soldUnits:number;bookings:number};
export type FollowUp={id:number;lead:{id:number;customerName:string};type:string;scheduledAt:string;remarks?:string;executive?:string;completed:boolean};
