// fileSlice.ts
import { createSlice, PayloadAction } from '@reduxjs/toolkit';

interface FileState {
  uploadedFile: string | null;
  downloadUrl: string | null;
  error: string | null;
}

const initialState: FileState = {
  uploadedFile: null,
  downloadUrl: null,
  error: null,
};

const fileSlice = createSlice({
  name: 'file',
  initialState,
  reducers: {
    fileUploaded: (state, action: PayloadAction<string>) => {
      state.uploadedFile = action.payload;
      state.error = null;
    },
    fileDownloaded: (state, action: PayloadAction<string>) => {
      state.downloadUrl = action.payload;
      state.error = null;
    },
    fileError: (state, action: PayloadAction<string>) => {
      state.error = action.payload;
    },
  },
});

export const { fileUploaded, fileDownloaded, fileError } = fileSlice.actions;
export const uploadFile = (file: File) => ({ type: 'file/uploadFile', payload: file });
export const downloadFile = (fileId: string) => ({ type: 'file/downloadFile', payload: fileId });

export default fileSlice.reducer;
