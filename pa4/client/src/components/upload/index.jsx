import React, { useState } from 'react';
import { PlusOutlined, LoadingOutlined } from '@ant-design/icons';
import { Upload } from 'antd';
import './style.css';
/* eslint-disable */
const MyUpload = (props) => {
  const {value, onChange} = props
  const [loading, setLoading] = useState(false);
  const uploadButton = (
    <div>
      {loading ? <LoadingOutlined /> : <PlusOutlined />}
      <div style={{ marginTop: 8 }}>上传</div>
    </div>
  );
  const handleChange = (info) => {
    if (info.file.status === 'uploading') {
      setLoading(true);
      return;
    }
    if (info.file.status === 'done') {
      setLoading(false);
      onChange(info.file.response.url)
    }
  }
  return (
    <Upload
      name="imgs"
      listType="picture-card"
      className="avatar-uploader"
      showUploadList={false}
      action="http://localhost:8080/api/upload"
      onChange={handleChange}
    >
      {value ? <img src={value} alt="avatar" style={{ width: '100%' }} /> : uploadButton}
    </Upload>
  )
}

export default MyUpload;

