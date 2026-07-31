const API='http://localhost:8080/api';
async function request<T>(path:string,options:RequestInit={}):Promise<T>{const headers=new Headers(options.headers);const token=localStorage.getItem('crm_token');if(token)headers.set('Authorization',`Bearer ${token}`);const r=await fetch(API+path,{...options,headers});if(r.status===401){localStorage.removeItem('crm_token');window.dispatchEvent(new Event('crm:logout'));throw new Error('Unauthorized')}if(!r.ok)throw new Error('Request failed');return r.json();}
export function get<T>(path:string):Promise<T>{return request<T>(path);}
export function post<T>(path:string,body:unknown):Promise<T>{return request<T>(path,{method:'POST',headers:{'Content-Type':'application/json'},body:JSON.stringify(body)});}
