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
        message.success("添加分类成功", 1, () => {
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
        message.success("删除成功", 1, () => {
          HandleRefresh();
        });
      } else {
        message.warning(res.data.message);
      }
    });
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
      title: "分类名称",
      dataIndex: "name",
      key: "name",
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
          添加
        </Button>
      </div>
      <Modal
        title="添加分类"
        cancelText="取消"
        okText="保存"
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
            label="分类"
            name="name"
            rules={[{ required: true, message: "分类名称不能为空" }]}
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
