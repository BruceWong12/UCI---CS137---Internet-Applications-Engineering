import React, { useState, useEffect } from 'react';
import GoodsDataService from "../../services/goods.service";
import CategorieDataService from "../../services/categorie.service";
import { Card, Form, Input, Select, Button } from 'antd';
import { Link } from 'react-router-dom'
import "./style.css";

const Home = () => {
  const [dataSource, setDataSource] = useState([]);
  const [categoryList, setCategoryList] = useState([])
  const HandleRefresh = (params={}) => {
    GoodsDataService.getAll(params).then((res) => {
      setDataSource(res.data);
    });
  };
  useEffect(() => {
    HandleRefresh()
    CategorieDataService.getAll().then((res) => {
      setCategoryList(res.data);
    });
  }, [])
  const onFinish = (values) => {
    HandleRefresh(values)
  }
  return (
    <>
      <section className='search'>
        <Form
          layout="inline"
          onFinish={onFinish}
          initialValues={{
            title: null
          }}
        >
        <Form.Item
          name="categoryId"
        >
          <Select placeholder="选择分类">
            {
              categoryList.map(item => {
                return <Select.Option key={item.id} value={item.id}>{item.name}</Select.Option>
              })
            }
          </Select>
        </Form.Item>
        <Form.Item
          name="price"
        >
          <Select placeholder="价格区间">
            <Select.Option value={1}>0 ~ 1000</Select.Option>
            <Select.Option value={2}>1000 ~ 5000</Select.Option>
            <Select.Option value={3}>5000 ~ 10000</Select.Option>
            <Select.Option value={4}>10000+</Select.Option>
          </Select>
        </Form.Item>
        <Form.Item
          name="title"
        >
          <Input placeholder='请搜索' />
        </Form.Item>
        <Form.Item>
          <Button type="primary" htmlType="submit">
            搜索
          </Button>
        </Form.Item>
      </Form>
      </section>
      <section className='content'>
        { dataSource.map(item => {
          return (
            <Link key={item.id} to={`/good/${item.id}`}>
              <Card
                hoverable
                style={{ width: 200 }}
                cover={<img alt={item.title} src={item.imgs} />}>
                <div>
                  <h3 style={{fontWeight: "normal", fontSize: '14px'}}>{item.title}</h3>
                  <p>
                    <span style={{color: '#f00', fontSize: "18px", marginRight: 10}}>￥{item.price}</span>
                    <span style={{color: '#ccc', fontSize: "12px"}}>{item.number}</span>
                  </p>
                </div>
              </Card>
            </Link>
          )
        }) }
      </section>
    </>
  )
}

export default Home;
