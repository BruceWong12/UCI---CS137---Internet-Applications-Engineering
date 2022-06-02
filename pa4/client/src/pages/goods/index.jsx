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
            message.success("Successfully delete");
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
    const data = formRef.current.getFieldsValue();
    const find = categoryList.find(item => item.id === data.categoryId)
    data.category = find ? find.name : null
    data.userId = "00ebd457-3604-49e8-8e77-82e892f94669"
    if (data.id) { // Edit
      GoodsDataService.update(data.id, data).then(res => {
        if (res.status === 200) {
          setVisible(false);
          message.success("Edit Successfully", 1, () => {
            HandleRefresh();
          });
        } else {
          message.warning(res.data.message);
        }
      })
    }
    // add
    else {
      GoodsDataService.create(data).then(res => {
        if (res.status === 200) {
          setVisible(false);
          message.success("Add Successfully", 1, () => {
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
      title: "Index",
      render: (text, round, index) => {
        return <span>{index + 1}</span>;
      },
      width: 55,
      align: "center",
      key: "id",
    },
    {
      title: "Status",
      dataIndex: "published",
      key: "published",
      width: 90,
      align: "center",
      render: (text) => {
        return <Switch checkedChildren="Published" unCheckedChildren="Pending" defaultChecked={text} />
      }
    },
    {
      title: "Image",
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
      title: "Title",
      dataIndex: "title",
      key: "title",
    },
    {
      title: "Price",
      dataIndex: "price",
      key: "price",
      width: 90,
      align: "right",
      render: (text) => {
        return `￥${text}`;
      },
    },
    {
      title: "Qty",
      dataIndex: "number",
      key: "number",
      align: "center",
      width: 80
    },
    {
      title: "Description",
      dataIndex: "description",
      key: "description",
    },
    {
      title: "Category",
      dataIndex: "category",
      key: "category",
      align: "center",
      width: 100
    },
    {
      title: "Create At",
      dataIndex: "createdAt",
      key: "createdAt",
      width: 160,
      align: "center",
      render: (text) => {
        return formatData(text);
      },
    },
    {
      title: "Update At",
      dataIndex: "updatedAt",
      key: "updatedAt",
      width: 160,
      align: "center",
      render: (text) => {
        return formatData(text);
      },
    },
    {
      title: "Edit",
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
          Add
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
        title="Add Products"
        cancelText="Cancel"
        okText="Save"
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


          <Row gutter={20}>
            <Col span={12}>
              <Form.Item
                label="Title"
                name="title"
                rules={[{ required: true, message: "Title can't be empty" }]}
              >
                <Input placeholder="Input title" />
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item label="Category" name="categoryId" rules={[{ required: true, message: "Category can't be empty" }]}>
                <Select placeholder="Please choose category">
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
                label="Price"
                name="price"
                rules={[{ required: true, message: "Price can't be empty" }]}
              >
                <InputNumber style={{width: 180}} placeholder="Please input price" />
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item
                label="Qty"
                name="number"
                rules={[{ required: true, message: "Qty can't be empty" }]}
              >
                <InputNumber style={{width: 180}} placeholder="Please input qty"/>
              </Form.Item>
            </Col>
          </Row>
          <Form.Item
            label="image"
            name="imgs"
          >
            <Upload />
          </Form.Item>
          <Form.Item
            label="Description"
            name="description"
          >
            <TextArea rows={4} placeholder="Please type in description" />
          </Form.Item>
          <Form.Item name="published" valuePropName="checked">
            <Checkbox>Publish</Checkbox>
          </Form.Item>
        </Form>
      </Modal>
    </section>
  );
};
export default User;
