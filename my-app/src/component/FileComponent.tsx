// FileComponent.tsx
import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { uploadFile, downloadFile } from '../redux/fileSlice';

const FileComponent = () => {
  const dispatch = useDispatch();
  const [selectedFile, setSelectedFile] = useState<File | null>(null);

  const handleFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    if (event.target.files && event.target.files.length > 0) {
      setSelectedFile(event.target.files[0]);
    }
  };

  const handleUpload = () => {
    if (selectedFile) {
      dispatch(uploadFile(selectedFile));
    }
  };

  const handleDownload = () => {
    // Replace with the appropriate file ID
    const fileId = 'your-file-id';
    dispatch(downloadFile(fileId));
  };

  const downloadUrl = useSelector((state: any) => {
    console.log(state);
   return "http://localhost:8090"+state.file.uploadedFile});
  const error = useSelector((state: any) => state.file.error);

  return (
    <div>
      <input type="file" onChange={handleFileChange} />
      <button onClick={handleUpload}>Upload File</button>
      <button onClick={handleDownload}>Download File</button>
      {error && <p>Error: {error}</p>}
      {downloadUrl && (
        <div>
          <a href={downloadUrl} download>
            Download File
          </a>
          <br />
          <img src={downloadUrl} title="Download"  />
        </div>
      )}
    </div>
  );
};

export default FileComponent;
