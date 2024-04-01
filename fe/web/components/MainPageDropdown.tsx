import {
  Button,
  Dropdown,
  DropdownItem,
  DropdownMenu,
  DropdownTrigger,
} from '@nextui-org/react';
import { useRouter } from 'next/router';
// import classes from './MainPageDropdown.module.scss';
import DropdownMenuIcon from './icons/DropdownMenuIcon';
import { setMainCard } from '@/api/paymentAxios';

function MainPageDropdown() {
  // const setMain = (id:number) => {
  //   setMainCard(id)
  // }
  const router = useRouter();
  return (
    <Dropdown>
      <DropdownTrigger>
        <button>
          <DropdownMenuIcon />
        </button>
      </DropdownTrigger>
      <DropdownMenu aria-label="Static Actions">
        <DropdownItem key="new" onClick={() => router.push('/card')}>
          카드 등록
        </DropdownItem>
        <DropdownItem key="copy">대표 카드 등록</DropdownItem>
        <DropdownItem key="delete" className="text-danger" color="danger">
          카드 삭제
        </DropdownItem>
      </DropdownMenu>
    </Dropdown>
  );
}
export default MainPageDropdown;
