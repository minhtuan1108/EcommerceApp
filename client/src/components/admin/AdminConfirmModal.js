import {Button, Modal} from "flowbite-react";
import {HiOutlineExclamationCircle} from "react-icons/hi";
import {useState} from "react";

const AdminConfirmModal = ({isShow, closeModal, content, confirmCallback}) => {
    return <>
        <Modal show={isShow} size="md" onClose={closeModal} popup>
            <Modal.Header />
            <Modal.Body>
                <div className="text-center">
                    <HiOutlineExclamationCircle className="mx-auto mb-4 h-14 w-14 text-gray-400 dark:text-gray-200" />
                    <h3 className="mb-5 text-lg font-normal text-gray-500 dark:text-gray-400">
                        {content}
                    </h3>
                    <div className="flex justify-center gap-4">
                        <Button color="failure" onClick={() => {
                            closeModal();
                            confirmCallback();
                        }}>
                            Xác nhận
                        </Button>
                        <Button color="gray" onClick={closeModal}>
                            Không
                        </Button>
                    </div>
                </div>
            </Modal.Body>
        </Modal>
    </>
}

export default AdminConfirmModal;