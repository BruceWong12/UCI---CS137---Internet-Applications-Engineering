import React, { useEffect, useState, useRef } from "react";
import GoodsDataService from "../../services/goods.service";
import CategorieDataService from "../../services/categorie.service";
import { Button, Table, message, Modal, Form, Input, InputNumber, Switch, Checkbox, Select, Row, Col } from "antd";
import Upload from '../../components/upload/index'
import formatData from "../../utils/formatDate";
import { PlusOutlined, EditOutlined, DeleteOutlined } from "@ant-design/icons";
import "./index.css";

const { TextArea } = Input;
const initialValues = {
  id: null,
  title: null,
  imgs: null,
  price: null,
  number: null,
  user: "admin",
  userId: "00ebd457-3604-49e8-8e77-82e892f94669",
  description: null,
  published: false
}

const User = () => {
  const formRef = useRef(null);
  const [categoryList, setCategoryList] = useState([])
  const [values, setValues] = useState(initialValues);
  const [visible, setVisible] = useState(false);
  const [dataSource, setDataSource] = useState([]);
  const [loading, setLoading] = useState(false);
  useEffect(() => {
    HandleRefresh();
    CategorieDataService.getAll().then((res) => {
      setCategoryList(res.data);
    });
  }, []);
  const HandleRefresh = () => {
    setLoading(true);
    GoodsDataService.getAll().then((res) => {
      setDataSource(res.data);
      setLoading(false);
    });
  };
  const handleAction = (type, record) => {
    switch (type) {
      case "add":
        setValues(Object.assign({}, initialValues))
        setVisible(true)
        break
      case "edit":
        setValues(Object.assign({}, initialValues, record))
        setVisible(true)
        break
      case "delete":
        GoodsDataService.delete(record.id).then((res) => {
          if (res.status === 200) {
            message.success("删除成功");
            HandleRefresh();
          } else {
            message.warning(res.data.message);
          }
        });
        break;
      default:
        break;
    }
  };
  const handleOk = () => {
    formRef.current.validateFields()
    if (!formRef.current.isFieldsTouched()) {
      return
    }
    const datas = formRef.current.getFieldsValue();
    const find = categoryList.find(item => item.id === datas.categoryId)
    datas.category = find ? find.name : null
    datas.userId = "00ebd457-3604-49e8-8e77-82e892f94669"
    if (datas.id) { // 编辑
      GoodsDataService.update(datas.id, datas).then(res => {
        if (res.status === 200) {
          setVisible(false);
          message.success("编辑成功", 1, () => {
            HandleRefresh();
          });
        } else {
          message.warning(res.data.message);
        }
      })
    } else { // 添加
      GoodsDataService.create(datas).then(res => {
        if (res.status === 200) {
          setVisible(false);
          message.success("添加成功", 1, () => {
            HandleRefresh();
          });
        } else {
          message.warning(res.data.message);
        }
      })
    }
  };
  const columns = [
    {
      title: "序号",
      render: (text, round, index) => {
        return <span>{index + 1}</span>;
      },
      width: 55,
      align: "center",
      key: "id",
    },
    {
      title: "状态",
      dataIndex: "published",
      key: "published",
      width: 90,
      align: "center",
      render: (text) => {
        return <Switch checkedChildren="上架" unCheckedChildren="下架" defaultChecked={text} />
      }
    },
    {
      title: "图片",
      dataIndex: "imgs",
      key: "imgs",
      render: (text) => {
        return <div style={{
          width: '100px',
          height: '100px',
          border: "1px solid #eee",
          backgroundImage: `url(${text})`,
          backgroundSize: 'contain',
          backgroundPosition: "center center",
          backgroundRepeat: "no-repeat"
        }}></div>
      }
    },
    {
      title: "标题",
      dataIndex: "title",
      key: "title",
    },
    {
      title: "价格",
      dataIndex: "price",
      key: "price",
      width: 90,
      align: "right",
      render: (text) => {
        return `￥${text}`;
      },
    },
    {
      title: "数量",
      dataIndex: "number",
      key: "number",
      align: "center",
      width: 80
    },
    {
      title: "描述",
      dataIndex: "description",
      key: "description",
    },
    {
      title: "分类",
      dataIndex: "category",
      key: "category",
      align: "center",
      width: 100
    },
    {
      title: "作者",
      dataIndex: "user",
      width: 80,
      key: "user",
    },
    {
      title: "创建时间",
      dataIndex: "createdAt",
      key: "createdAt",
      width: 160,
      align: "center",
      render: (text) => {
        return formatData(text);
      },
    },
    {
      title: "更新时间",
      dataIndex: "updatedAt",
      key: "updatedAt",
      width: 160,
      align: "center",
      render: (text) => {
        return formatData(text);
      },
    },
    {
      title: "操作",
      key: "id",
      align: "center",
      width: 70,
      render: (text, record) => {
        return (
          <span>
            <EditOutlined
              style={{
                cursor: "pointer",
                color: "#1890ff",
                margin: "0 5px 0 0",
              }}
              onClick={() => handleAction("edit", record)}
            />
            <DeleteOutlined
              style={{ cursor: "pointer", color: "#f00" }}
              onClick={() => handleAction("delete", record)}
            />
          </span>
        );
      },
    },
  ];
  return (
    <section className="container">
      <div style={{ textAlign: "right", margin: "0 0 15px 0" }}>
        <Button
          type="primary"
          icon={<PlusOutlined />}
          onClick={() => handleAction("add")}
        >
          添加
        </Button>
      </div>
      <Table
        loading={loading}
        rowKey="id"
        size="small"
        dataSource={dataSource}
        columns={columns}
      />
      <Modal
        title="添加商品"
        cancelText="取消"
        okText="保存"
        visible={visible}
        onOk={handleOk}
        onCancel={() => setVisible(false)}
        width={900}
        destroyOnClose
      >
        <Form
          name="basic"
          layout="vertical"
          initialValues={values}
          autoComplete="off"
          ref={formRef}
        >
          <Form.Item
            label="Id"
            name="id"
            style={{display: 'none'}}
          >
            <Input />
          </Form.Item>
          <Form.Item
            label="用户"
            name="user"
            style={{display: 'none'}}
          >
            <Input />
          </Form.Item>
          <Form.Item
            label="用户Id"
            name="userId"
            style={{display: 'none'}}
          >
            <Input />
          </Form.Item>
          <Row gutter={20}>
            <Col span={12}>
              <Form.Item
                label="标题"
                name="title"
                rules={[{ required: true, message: "标题不能为空" }]}
              >
                <Input placeholder="请输入标题" />
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item label="分类" name="categoryId" rules={[{ required: true, message: "分类不能为空" }]}>
                <Select placeholder="请选择分类">
                  {
                    categoryList.map(item => <Select.Option key={item.id} value={item.id}>{item.name}</Select.Option> )
                  }
                </Select>
              </Form.Item>
            </Col>
          </Row>
          <Row gutter={20}>
            <Col span={12}>
              <Form.Item
                label="金额"
                name="price"
                rules={[{ required: true, message: "标题不能为空" }]}
              >
                <InputNumber style={{width: 180}} placeholder="请输入金额" />
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item
                label="数量"
                name="number"
                rules={[{ required: true, message: "标题不能为空" }]}
              >
                <InputNumber style={{width: 180}} placeholder="请输入数量"/>
              </Form.Item>
            </Col>
          </Row>
          <Form.Item
            label="图片"
            name="imgs"
          >
            <Upload />
          </Form.Item>
          <Form.Item
            label="描述"
            name="description"
          >
            <TextArea rows={4} placeholder="请输入描述" />
          </Form.Item>
          <Form.Item name="published" valuePropName="checked">
            <Checkbox>是否上架</Checkbox>
          </Form.Item>
        </Form>
      </Modal>
    </section>
  );
};

export default User;
