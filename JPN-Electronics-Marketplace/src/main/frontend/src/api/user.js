import {post} from './http'

export const login=(user)=>post('api/user/login',user)
