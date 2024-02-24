import {post} from './http'

export const login=(user)=>post('api/user/login',user)
export const register=(user)=>post('api/user/register',user)
