import React, { useEffect, useRef, useState } from "react";
import CategorieDataService from "../../services/categorie.service";
import { Button, Table, Modal, Form, Input, message } from "antd";
import formatData from "../../utils/formatDate";
import { PlusOutlined, DeleteOutlined } from "@ant-design/icons";

import "./index.css";
const Categorie = () => {
  const formRef = useRef(null);
  const [values, setValues] = useState({
    name: null,
  });
  const [visible, setVisible] = useState(false);
  const [dataSource, setDataSource] = useState([]);
  const HandleRefresh = () => {
    CategorieDataService.getAll().then((res) => {
      setDataSource(res.data);
    });
  };
  useEffect(() => {
    HandleRefresh();
  }, []);
  const handleOk = () => {
    const datas = formRef.current.getFieldsValue();
    CategorieDataService.create(datas).then((res) => {
      if (res.status === 200) {
        message.success("Successfully add category", 1, () => {
          HandleRefresh();
        });
        setVisible(false);
      } else {
        message.warning(res.data.message);
      }
    });
  };
  const handleCancel = () => {
    setVisible(false);
  };
  const handleDelete = ({ id }) => {
    CategorieDataService.delete(id).then((res) => {
      if (res.status === 200) {
        message.success("Successfully delete", 1, () => {
          HandleRefresh();
        });
      } else {
        message.warning(res.data.message);
      }
    });
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
      title: "Category Name",
      dataIndex: "name",
      key: "name",
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
            <DeleteOutlined
              style={{ cursor: "pointer", color: "#f00" }}
              onClick={() => handleDelete(record)}
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
          onClick={() => {
            setValues({name: null})
            setVisible(true)
          }}
        >
          Add
        </Button>
      </div>
      <Modal
        title="Add Category"
        cancelText="Cancel"
        okText="Save"
        visible={visible}
        onOk={handleOk}
        onCancel={handleCancel}
      >
        <Form
          name="basic"
          labelCol={{ span: 4 }}
          wrapperCol={{ span: 16 }}
          initialValues={values}
          autoComplete="off"
          ref={formRef}
        >
          <Form.Item
            label="Category"
            name="name"
            rules={[{ required: true, message: "Category can't be empty" }]}
          >
            <Input />
          </Form.Item>
        </Form>
      </Modal>
      <Table
        rowKey="id"
        size="small"
        dataSource={dataSource}
        columns={columns}
      />
    </section>
  );
};

export default Categorie;
