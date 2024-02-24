import {get, post} from './http'

export const getProductsExceptUsers=(pageParam)=>get('/api/product/shop')
export const getMyProducts=(pageParam)=>get('/api/product/myProducts')
export const searchProducts=(pageParam, key)=>get('/api/product/searchProducts')
export const postNewProduct=(product)=>post('/api/product/addProduct', product)
export const sellProduct=(productId)=>get('/api/product/sellProduct')
export const deleteProduct=(productId) =>get('/api/product/deleteProduct')
export const editProductName=(productId, newName)=>get('/api/product/editProductName')
export const editProductPrice=(productId, newPrice)=>get('/api/product/editProductPrice')
export const editProductDetail=(productId, newDescription)=>get('api/product/editProductInfo')


