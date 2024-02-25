import {get, post} from './http'

export const getProductsExceptUsers=(pageParam)=>post('/api/product/shop', pageParam)
export const getMyProducts=(pageParam)=>post('/api/product/myProducts', pageParam)
export const searchProducts=(pageParam, key)=>get('/api/product/searchProducts')
export const postNewProduct=(product)=>post('/api/product/addProduct', product)
export const sellProduct=(productId)=>get('/api/product/sellProduct')
export const deleteProduct=(productId) =>get('/api/product/deleteProduct', productId)
export const editProductName=(productId, newName)=>get('/api/product/editProductName')
export const editProductPrice=(productId, newPrice)=>get('/api/product/editProductPrice')
export const editProductDetail=(productId, newDescription)=>get('api/product/editProductInfo')


