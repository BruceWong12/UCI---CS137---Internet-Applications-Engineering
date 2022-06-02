// import React, { useEffect, useRef } from 'react';
// import { PlusOutlined, DeleteOutlined } from '@ant-design/icons';
// import './style.css';
// /* eslint-disable */
// const Upload = (props) => {
  
//   const { value, onChange } = props
//   const ref = useRef(null)
//   const handleChange = function() {
//     const reader = new FileReader();
//     reader.readAsDataURL(this.files[0]);
//     reader.onload = function(e){
//       onChange(e.target.result)
//     };
//   }
//   useEffect(() => {
//     ref.current && ref.current.addEventListener('change', handleChange, false)
//     return () => {
//       ref.current && ref.current.removeEventListener('change', handleChange, false)
//     }
//   }, [])
//   return <section className='upload' style={{
//     backgroundImage: `url(${value})`
//   }}>
//     { value ? <div className='delete'><DeleteOutlined onClick={() => onChange(null)} style={{color: '#f00', cursor: 'pointer'}} /></div> : (
//       <>
//         <input type='file' ref={ref} accept=".jpg,.png,.bmp,.jpeg" />
//         <PlusOutlined />
//       </>
//     )}
//   </section>
// }

// export default Upload;

