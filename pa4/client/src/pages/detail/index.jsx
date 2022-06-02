import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import GoodsDataService from "../../services/goods.service";
import './style.css'

const Detail = () => {
  const [data, setData] = useState({})
  const params = useParams()
  const { id } = params
  useEffect(() => {
    GoodsDataService.get(id).then(res => {
      if (res.status === 200) {
        setData(res.data);
      }
    })
  }, [id])
  if (Object.keys(data).length < 1) {
    return <section className='detail'>
      <span className='no-data'>暂无此商品</span>
    </section>
  }
  return <section className='detail'>
    <dl>
      <dt>
        <img src={data.imgs} alt={data.title}></img>
      </dt>
      <dd>
        <h2>{data.title}</h2>
        <p className='price'>
          价格:<span>￥{data.price}</span>
        </p>
        <p className='number'>
          数量:<span>{data.number}</span>
        </p>
      </dd>
    </dl>
    <div className='content'>
      {data.description}
    </div>
  </section>
}
export default Detail