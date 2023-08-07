// fileSaga.ts
import { call, put, takeEvery } from 'redux-saga/effects';
import axios from 'axios';
import { fileUploaded, fileDownloaded, fileError } from '../redux/fileSlice';

function* uploadFile(action: any): Generator<any, any, any> {
  try {
    const formData = new FormData();
    formData.append('image', action.payload);

    const response = yield call(axios.post, 'http://localhost:8090/api/file', formData);
    yield put(fileUploaded(response.data.url));
  } catch (error) {
    const errorMessage = (error as Error).message; 
    yield put(fileError(errorMessage));
  }
}

function* downloadFile(action: any): Generator<any, any, any> {
  try {
    const response = yield call(axios.get, `/api/download/${action.payload}`, {
      responseType: 'blob',
    });

    const blob = new Blob([response.data], { type: 'application/octet-stream' });
    const downloadUrl = URL.createObjectURL(blob);

    yield put(fileDownloaded(downloadUrl));
  } catch (error) {
    const errorMessage = (error as Error).message; 
    yield put(fileError(errorMessage));
  }
}

function* fileSaga() {
  yield takeEvery('file/uploadFile', uploadFile);
  yield takeEvery('file/downloadFile', downloadFile);
}

export default fileSaga;
